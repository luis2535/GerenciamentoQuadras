package br.com.example.gerenciamento.persistencia;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;

public class Conexao {

    private static MongoClient mongoClient = null;
    private static MongoDatabase db = null;

    private Conexao() {}

    public static MongoDatabase getConexao() throws MongoException {
        if (db == null) {
            String host = "localhost";
            int porta = 27017;
            String nomeBanco = "GerenciamentoQuadras";

            mongoClient = MongoClients.create("mongodb://" + host + ":" + porta);
            db = mongoClient.getDatabase(nomeBanco);
        }
        return db;
    }
}
