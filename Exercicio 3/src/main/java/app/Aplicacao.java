package app;

import static spark.Spark.*;

import service.JogadorService;

public class Aplicacao {
	
	private static JogadorService jogadorService = new JogadorService();
	
    public static void main(String[] args) {
        port(6789);

        post("/jogador", jogadorService::add);
        get("/jogador/:id", jogadorService::get);
        get("/jogador/update/:id", jogadorService::update);
        get("/jogador/delete/:id", jogadorService::remove);
        get("/jogador", jogadorService::getAll);
    }
}
