package ru.chat.client;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    TextArea chatArea;  // многострочное текстовое поле чата

    @FXML
    TextField messageField, usernameField; // однострочное текстовое поле для отправки сообщений на сервер

    @FXML
    HBox authPanel, msgPanel;

    @FXML
    ListView<String> clientsListView;

    private Socket socket;  // соединение с сервером
    private DataInputStream in;
    private DataOutputStream out;

    public void setAuthorized(boolean authorized) {     //если авторизованы
        msgPanel.setVisible(authorized);
        msgPanel.setManaged(authorized);
        authPanel.setVisible(!authorized);
        authPanel.setManaged(!authorized);
        clientsListView.setVisible(authorized);
        clientsListView.setManaged(authorized);
    }

    public void sendMessage() {   // по нажатии sendMessage шлём сообщение на сервер
        try {
            out.writeUTF(messageField.getText()); // отправка сообщений серверу, кот. написано в однострочном поле
            messageField.clear();
            messageField.requestFocus(); // переброска фокуса(курсора) на поле отправки сообщений messageField
        } catch (IOException e) {
            showError("Невозможно отправить сообщение на сервер");
        }
    }

    public void sendCloseRequest() {   // запрос на прекращение соединения при закрытии окна
        try {
            if (out != null) {
                out.writeUTF("/exit"); // отправка клиентом сообщения о прекращении чата
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryToAuth() {   // авторизация
        connect();
        try {
            out.writeUTF("/auth " + usernameField.getText());
            usernameField.clear();
        } catch (IOException e) {
            showError("Невозможно отправить запрос об авторизации на сервер");
        }
    }

    public void connect() {
        if (socket != null && !socket.isClosed()) {
            return;
        }
        try {
            socket = new Socket("localhost", 8189);   // открыли соединение с клиентом
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> mainClientLogic()).start();
            ; // отдельный поток для получения сообщений от сервера

        } catch (IOException e) {
            showError("Невозможно подключиться к сети");
        }
    }

    private void mainClientLogic() {
        try {
            while (true) {
                String inputMessage = in.readUTF();  // ожидание сообщения от сервера, блокирующая операция
                if (inputMessage.equals("/exit")) {
                    closeConnection();
                }
                if (inputMessage.equals("/authok")) {
                    setAuthorized(true);
                    break;
                }
                chatArea.appendText(inputMessage + "\n"); // закидываем сообщение в поле чата
            }
            while (true) {
                String inputMessage = in.readUTF();  // ожидание сообщения от сервера, блокирующая операция
                System.out.println(inputMessage);
                if (inputMessage.startsWith("/")) {
                    if (inputMessage.startsWith("/exit")) {
                        break;
                    }
                    if(inputMessage.startsWith("/clients_list ")) {
                        Platform.runLater(() -> {
                            String[] tokens = inputMessage.split("\\s+");
                            clientsListView.getItems().clear();
                            for(int i = 1; i < tokens.length; i++) {
                                clientsListView.getItems().add(tokens[i]);
                            }
                        });

                    }
                    continue;
                }
                chatArea.appendText(inputMessage + "\n"); // закидываем сообщение в поле чата
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void closeConnection() {   // закрываем соединение после отсоединения клиента
        setAuthorized(false);
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void showError(String message) {
        new Alert(Alert.AlertType.ERROR, message, ButtonType.OK).showAndWait(); // всплывающее сообщение об ошибке с кнопкой ОК
    }

    public void clientsListDoubleClick(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2) { // если дважды кликнули по clientListView
            String selectedUser = clientsListView.getSelectionModel().getSelectedItem();  // куда кликнул пользователь
            messageField.setText("/w " + selectedUser + " ");
            messageField.requestFocus();
            messageField.selectEnd();
        }
    }
}