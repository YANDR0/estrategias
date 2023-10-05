
class Borrego implements Comparable<Borrego>{

    private int kilos;
    private String nombre;

    public Borrego(int kilograms, String name){
        this.kilos = kilograms;
        this.nombre = name;
    }

    public String toString(){
        return String.format("%d kilos: borrego %s. ¡Mbaaah!", kilos, nombre);
    }

    @Override
    public int compareTo(Borrego b) {
        return this.kilos - b.kilos != 0? this.kilos - b.kilos: this.nombre.compareTo(b.nombre);
    }
}

