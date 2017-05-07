package Proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by andrei on 2017-05-07.
 */
public interface IObserverService extends Remote {
    void newParticipantRegistered(String ageCategoryName, List<String> trials) throws RemoteException;
}