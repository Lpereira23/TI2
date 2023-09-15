package dao;

import model.Jogador;

public class Principal {
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		if (dao.conectar()) {
			// Inserir
			Jogador jogador = new Jogador(11, "Dudu", "Futebol", "Palmeiras");
			if (dao.executarUpdate("INSERT INTO Jogadores (ID, Nome, Esporte, Clube) VALUES (" +
			                     jogador.getID() + ", '" + jogador.getNome() + "', '" +
			                     jogador.getEsporte() + "', '" + jogador.getClube() + "');")) {
				System.out.println("Inserção com sucesso -> " + jogador.toString());
			}

			// Atualizar
			jogador.setEsporte("nova esporte");
			if (dao.executarUpdate("UPDATE Jogadores SET Nome = '" + jogador.getNome() +
			                     "', Esporte = '" + jogador.getEsporte() + "', Clube = '" +
			                     jogador.getClube() + "' WHERE ID = " + jogador.getID())) {
				System.out.println("Atualização com sucesso -> " + jogador.toString());
			}

			// Excluir 
		if (dao.executarUpdate("DELETE FROM Jogadores WHERE ID = " + jogador.getID())) {
				System.out.println("Exclusão com sucesso -> " + jogador.toString());
			}

			// Mostrar 
			Jogador[] jogadores = dao.getJogadores();
			if (jogadores != null) {
				System.out.println("==== Mostrar usuários === ");
				for (Jogador j : jogadores) {
					System.out.println(j.toString());
				}
			}

			dao.close();
		} else {
			System.out.println("Não foi possível conectar ao banco de dados.");
		}
	}
}
