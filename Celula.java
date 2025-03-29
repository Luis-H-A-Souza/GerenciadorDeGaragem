package executable;

public class Celula {
	private Carro info;
	private Celula proximo;

	public Carro getInfo() {
		return info;
	}

	public void setInfo(Carro info) {
		this.info = info;
	}

	public Celula getProximo() {
		return proximo;
	}

	public void setProximo(Celula proximo) {
		this.proximo = proximo;
	}
}
