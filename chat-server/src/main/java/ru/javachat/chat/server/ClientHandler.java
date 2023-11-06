package ru.javachat.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private Server server;  // ссылка на сервер
    private Socket socket;  // для запоминания socket клиента - сетевое соединение с клиентом
    private String username;
    private DataInputStream in; // запоминание ссылки на вх. поток
    private DataOutputStream out; // запоминание ссылки на исх. поток

    public String getUsername() {
        return username;
    }

    public ClientHandler(Server server, Socket socket) {

        try {
            this.server = server;   // запоминание сервера
            this.socket = socket;   // запоминание сокета соединения с клиентом
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> logic()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendMessage(String message) {  // отправка сообщения клиенту
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void logic() {   // основная логика
        try {
            while (!consumeAuthorizeMessage(in.readUTF())); // авторизация клиента
            while (comnsumeRegularMessage(in.readUTF()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Клиент " + username + " отключился");
            server.unsubscribe(this); // после отсоединения клиента отписались от рассылки
            closeConnection(); // закрываем соединение с клиентом
        }
    }

    private boolean comnsumeRegularMessage(String inputMessage) {  // работа с обычными сообщениями

            if (inputMessage.startsWith("/")) {
                if (inputMessage.equals("/exit")) { // получение от клиента запроса о прекращении соединения
                    sendMessage("/exit"); // ответ сервера о прекращении соединения
                    return false;
                }
                if (inputMessage.startsWith("/w ")) {
                    String[] tokens = inputMessage.split("\\s+", 3);
                    server.sendPersonalMessage(this, tokens[1], tokens[2]);
                }
                return true;
            }
            server.broadcastMessage(username + ": " + inputMessage);  //рассылка сообщения всем
        return true;
        }


    private boolean consumeAuthorizeMessage(String message) {   // авторизация
        if (message.startsWith("/auth ")) {
            String[] tokens = message.split("\\s+");
            if (tokens.length == 1) {
                sendMessage("SERVER: Вы не указали имя пользователя");
                return false;
            }
            if (tokens.length > 2) {
                sendMessage("SERVER: Имя пользователя не может состоять из нескольких слов");
                return false;
            }
            String selectedUsername = tokens[1];
            if (server.isUsernameUsed(selectedUsername)) {  // проверка имени клиента
                sendMessage("SERVER: Данное имя пользователя уже занято");
                return false;
            }
            username = selectedUsername; // если имя не занять
            sendMessage("/authok"); // по этому сообщению понимаем, что вошли на сервер
            server.subscribe(this); // добавляем в список(рассылку)
            return true;
        } else {
            sendMessage("SERVER: Вам необходимо авторизоваться");
            return false;
        }
    }

    public void closeConnection() {   // закрываем соединение после отсоединения клиента

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

}
