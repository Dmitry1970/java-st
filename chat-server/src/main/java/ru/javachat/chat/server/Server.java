package ru.javachat.chat.server;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private List<ClientHandler> clients;  // список обработчиков клиентов

    public Server() {
        try {
            this.clients = new ArrayList<>();
            ServerSocket serverSocket = new ServerSocket(8189); // запуск сервера на порту 8189
            System.out.println("Сервер запущен. Ожидаем подключение клиентов...");
            while (true) {
                Socket socket = serverSocket.accept();  // ожидание подключающихся, блокирующая операция
                System.out.println("Подключился новый клиент");
                new ClientHandler(this, socket);  // запускаем обработчик клиентов - логика общения с клиентом
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public synchronized void subscribe(ClientHandler c) {  // добавить клиента в список рассылки
        clients.add(c);
        this.broadcastMessage("В чат вошёл " + c.getUsername());
        broadcastClientList();
    }

    public synchronized void unsubscribe(ClientHandler c) {  // убрать клиента из списка рассылки
        clients.remove(c);
        this.broadcastMessage("Из чата вышел " + c.getUsername());
        broadcastClientList();
    }



    public synchronized void broadcastMessage(String message) {   // отправка сообщения всем авторизованным клиентам
        for(ClientHandler c : clients) {
            c.sendMessage(message);
        }
    }

    public synchronized void broadcastClientList() {   // список клиентов
        StringBuilder builder = new StringBuilder(clients.size() * 10);
        builder.append("clients_list");
        for(ClientHandler c : clients) {
            builder.append(c.getUsername()).append(" ");
        }
        String clientsListStr = builder.toString();
        broadcastMessage(clientsListStr);
    }

    public synchronized boolean isUsernameUsed(String username) {  // проверка на занятость имени клиента
        for(ClientHandler c : clients) {
            if(c.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void sendPersonalMessage(ClientHandler sender, String recieverUsername, String message) {
        if(sender.getUsername().equalsIgnoreCase(recieverUsername)) {
            sender.sendMessage("Нельзя отправлять сообщения самому себе");
            return;
        }
        for(ClientHandler c : clients) {
            if(c.getUsername().equalsIgnoreCase(recieverUsername)) {
                c.sendMessage("От " + sender.getUsername() + ": " + message);  // личное сообщение другому пользователю
                sender.sendMessage("пользователю " + recieverUsername + ": " + message);  // история сообщений
                return;
            }
        }
        sender.sendMessage("Пользователь " + recieverUsername + " не в сети");
    }
}
