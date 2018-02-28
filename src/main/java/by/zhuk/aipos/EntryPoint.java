package by.zhuk.aipos;

import by.zhuk.aipos.service.ArticleServiceStub;
import org.apache.axis2.AxisFault;

import java.rmi.RemoteException;

public class EntryPoint {
    public static void main(String[] args) {
        try {
            ArticleServiceStub stub = new ArticleServiceStub();
            stub.findArticle(new ArticleServiceStub.FindArticle());
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}