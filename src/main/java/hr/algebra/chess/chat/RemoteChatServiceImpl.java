package hr.algebra.chess.chat;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class RemoteChatServiceImpl implements RemoteChatService {

    List<String> chatMesagesList;

    public RemoteChatServiceImpl() {
        chatMesagesList = new ArrayList<>();
    }

    @Override
    public void sendChatMessage(String chatMessage) throws RemoteException {
        chatMesagesList.add(chatMessage);
    }

    @Override
    public List<String> getAllChatMessages() throws RemoteException {
        return chatMesagesList;
    }
}
