package zonapsykhe.zonapsykhe.episodes;

public class BotonTextoImagenAsociacion {
    private int botonId;
    private int textoId;
    private int imageId;

    public BotonTextoImagenAsociacion(int botaoId, int textoId, int imageId) {
        this.botonId = botaoId;
        this.textoId = textoId;
        this.imageId = imageId;
    }

    public int getBotonId() {
        return botonId;
    }

    public int getTextoId() {
        return textoId;
    }

    public int getImageId() {
        return imageId;
    }

    @Override
    public String toString() {
        return "BotonTextoAsociacion{" +
                "botonId='" + botonId + '\'' +
                ", textoId='" + textoId + '\'' +
                ", imageId='" + imageId + '\'' +
                '}';
    }
}
