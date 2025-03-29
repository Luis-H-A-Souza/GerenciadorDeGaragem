package executable;

import java.util.Scanner;

public class Garagem {
	private static int proximoId = 1;
	Scanner sc = new Scanner(System.in);
	
	private Celula primeiro;
	private Celula ultimo;

	public Celula getPrimeiro() {
		return primeiro;
	}

	public void setPrimeiro(Celula primeiro) {
		this.primeiro = primeiro;
	}

	public Celula getUltimo() {
		return ultimo;
	}

	public void setUltimo(Celula ultimo) {
		this.ultimo = ultimo;
	}
	
	public void adicionar(Carro info) {
		Celula celula = new Celula();
		celula.setInfo(info);

		if (primeiro == null && ultimo == null) {
			primeiro = celula;
			ultimo = celula;
		} else {
			ultimo.setProximo(celula);
			ultimo = celula;
		}
	}
	
	public void addCarro() {
		Carro carro = new Carro();

		carro.setId(proximoId);
		proximoId++;
		
		System.out.print("\nInforme o nome do seu carro: ");
		String nome = sc.nextLine();
		carro.setNome(nome);
		
		System.out.print("\nInforme o ano do carro: ");
		int ano = sc.nextInt();
		carro.setAno(ano);

		adicionar(carro);
		System.out.println("Carro adicionado com ID: " + carro.getId());
		sc.nextLine();
	}
	
	public void removerCarro(int id) {
	    if (primeiro == null) {
	        System.out.println("Lista vazia!");
	        return;
	    }

	    if (primeiro.getInfo().getId() == id) {
	        primeiro = primeiro.getProximo();
	        if (primeiro == null) {
	            ultimo = null;
	        }
	        System.out.println("Carro removido com sucesso!");
	        return;
	    }

	    Celula anterior = primeiro;
	    Celula atual = primeiro.getProximo();

	    while (atual != null) {
	        if (atual.getInfo().getId() == id) {
	            anterior.setProximo(atual.getProximo());
	            if (atual == ultimo) {
	                ultimo = anterior;
	            }
	            System.out.println("Carro removido com sucesso!");
	            return;
	        }
	        anterior = atual;
	        atual = atual.getProximo();
	    }

	    System.out.println("Carro com ID " + id + " não encontrado!");
	}
	
	public Carro procurarId(int id) {
		Celula atual = primeiro;
		while (atual != null) {
			if (atual.getInfo().getId().equals(id)) {
				return atual.getInfo();
			}
			atual = atual.getProximo();
		}
		return null;
	}
	
	public void listarCarros() {
		if (primeiro == null) {
			System.out.println("Nenhum carro cadastrado!");
			return;
		}

		Celula atual = primeiro;
		while (atual != null) {
			Carro c = atual.getInfo();
			System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome() + " | Ano: " + c.getAno());
			atual = atual.getProximo();
		}
	}
	
	public void editarCarro() {
		if (primeiro == null) {
			System.out.println("Lista de carros vazia!");
			return;
		}

		System.out.print("Digite o ID do carro que deseja editar: ");
		int id = sc.nextInt();
		sc.nextLine();

		Celula celulaAtual = primeiro;
		boolean encontrado = false;

		while (celulaAtual != null) {
			if (celulaAtual.getInfo().getId() == id) {
				encontrado = true;
				Carro carro = celulaAtual.getInfo();

				System.out.println("\nEditando carro:");
				System.out.println("ID: " + carro.getId());
				System.out.println("Nome atual: " + carro.getNome());
				System.out.println("Ano atual: " + carro.getAno());

				System.out.println("\nO que deseja editar?");
				System.out.println("1 - Nome");
				System.out.println("2 - Ano");
				System.out.println("3 - Ambos");
				System.out.print("Opção: ");
				int opcao = sc.nextInt();
				sc.nextLine();

				switch (opcao) {
				case 1:
					System.out.print("Novo nome: ");
					carro.setNome(sc.nextLine());
					break;
				case 2:
					System.out.print("Novo ano: ");
					carro.setAno(sc.nextInt());
					break;
				case 3:
					System.out.print("Novo nome: ");
					carro.setNome(sc.nextLine());
					System.out.print("Novo ano: ");
					carro.setAno(sc.nextInt());
					break;
				default:
					System.out.println("Opção inválida!");
					return;
				}

				System.out.println("Carro atualizado com sucesso!");
				break;
			}
			celulaAtual = celulaAtual.getProximo();
		}

		if (!encontrado) {
			System.out.println("Carro com ID " + id + " não encontrado!");
		}
	}
	
	public void abrirGaragem () {
		int opcao;

		do {
			System.out.println("\n=== MENU DE CARROS ===");
			System.out.println("1 - Adicionar novo carro");
			System.out.println("2 - Remover carro");
			System.out.println("3 - Editar carro");
			System.out.println("4 - Exibir todos os carros");
			System.out.println("5 - Buscar carro por ID");
			System.out.println("0 - Sair do programa");
			System.out.print("Escolha uma opção: ");

			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {
			case 1:
				addCarro();
				break;

			case 2:
				System.out.print("Digite o ID do carro a remover: ");
				int idRemover = sc.nextInt();
				removerCarro(idRemover);
				break;

			case 3:
				editarCarro();
				break;

			case 4:
				System.out.println("\n=== LISTA DE CARROS ===");
				listarCarros();
				break;

			case 5:
				System.out.print("Digite o ID do carro a buscar: ");
				int idBuscar = sc.nextInt();
				Carro carro = procurarId(idBuscar);
				if (carro != null) {
					System.out.println("\ncarro encontrado:");
					System.out.println("ID: " + carro.getId());
					System.out.println("Nome: " + carro.getNome());
					System.out.println("ano: " + carro.getAno());
				} else {
					System.out.println("carro não encontrado!");
				}
				break;

			case 0:
				System.out.println("Encerrando o programa...");
				break;

			default:
				System.out.println("Opção inválida! Tente novamente.");
			}

			if (opcao != 0) {
				System.out.println("\nPressione Enter para continuar...");
				sc.nextLine();
			}

		} while (opcao != 0);

		sc.close();
	}
}
