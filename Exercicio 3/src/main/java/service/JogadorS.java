package service;

import dao.DAO;
import model.Jogador;
import spark.Request;
import spark.Response;

public class JogadorS {
	private DAO jogadorDAO;

	public JogadorS() {
		jogadorDAO = new DAO();
	}

	public Object add(Request request, Response response) {
		String nome = request.queryParams("Nome");
		String esporte = request.queryParams("Esporte");
		String clube = request.queryParams("Clube");

		int ID = jogadorDAO.getMaxID() + 1;
		Jogador jogador = new Jogador(ID, nome, esporte, clube);

		if (jogadorDAO.executarUpdate("INSERT INTO Jogadores (ID, Nome, Esporte, Clube) VALUES (" +
		                             jogador.getID() + ", '" + jogador.getNome() + "', '" +
		                             jogador.getEsporte() + "', '" + jogador.getClube() + "');")) {
			response.status(201); // 201 Created
			return ID;
		} else {
			response.status(500); // Internal Server Error
			return "Erro ao inserir o jogador.";
		}
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Jogador jogador = jogadorDAO.get(id);
		
		if (jogador != null) {
    	    response.header("Content-Type", "application/json"); // Altere para JSON se preferir
            return jogador;
        } else {
            response.status(404); // 404 Not found
            return "Jogador não encontrado.";
        }
	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Jogador jogador = jogadorDAO.get(id);

        if (jogador != null) {
        	jogador.setNome(request.queryParams("Nome"));
        	jogador.setEsporte(request.queryParams("Esporte"));
        	jogador.setClube(request.queryParams("Clube"));

        	if (jogadorDAO.executarUpdate("UPDATE Jogadores SET Nome = '" + jogador.getNome() +
        		                     "', Esporte = '" + jogador.getEsporte() + "', Clube = '" +
        		                     jogador.getClube() + "' WHERE ID = " + jogador.getID())) {
        		response.status(200); // Success
                return "Jogador atualizado com sucesso.";
        	} else {
        		response.status(500); // Internal Server Error
                return "Erro ao atualizar o jogador.";
        	}
        } else {
            response.status(404); // 404 Not found
            return "Jogador não encontrado.";
        }
	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Jogador jogador = jogadorDAO.get(id);

        if (jogador != null) {
        	if (jogadorDAO.executarUpdate("DELETE FROM Jogadores WHERE ID = " + jogador.getID())) {
                response.status(200); // Success
                return "Jogador excluído com sucesso.";
            } else {
                response.status(500); // Internal Server Error
                return "Erro ao excluir o jogador.";
            }
        } else {
            response.status(404); // 404 Not found
            return "Jogador não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		Jogador[] jogadores = jogadorDAO.getJogadores();
		
		if (jogadores != null) {
			response.header("Content-Type", "application/json"); // Altere para JSON se preferir
			return jogadores;
		} else {
			response.status(500); // Internal Server Error
			return "Erro ao buscar jogadores.";
		}
	}
}
