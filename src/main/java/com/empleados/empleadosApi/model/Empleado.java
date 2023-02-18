package com.empleados.empleadosApi.model;
import javax.persistence.*;



//Entity nombre del la clase
//Con @Table se mapea la clase por la tabla si el nombre no es igual
@Entity(name="Empleado")
@Table( 
    name="empleado",
    uniqueConstraints = {
            @UniqueConstraint(name = "empleado_id_constraint", columnNames = "id")
    }
)
public class Empleado {
	@Id
	@SequenceGenerator(
		name = "generadorIdEmpleado",
		sequenceName = "EMPLEADO_GENERADOR_ID",
        initialValue=1,
		allocationSize = 1
	)

	@GeneratedValue(
		generator = "generadorIdEmpleado",
		strategy = GenerationType.SEQUENCE)

    @Column(name = "id",
            updatable = false,
            nullable=false, 
            unique=true
    )
	private Long id;

	@Column(name="nombre",
            nullable = false,
            columnDefinition = "TEXT")
	private  String nombre;

	@Column(name="apellido",
            nullable = false,
            columnDefinition = "TEXT")
	private String apellido;

	@Column(name="trabajo",
            nullable = false,
            columnDefinition = "TEXT")
	private  String trabajo;

  
    
    @OneToOne(
            mappedBy = "empleado",
            orphanRemoval = true
    )
    private Legajo legajoEmpleado;



	public Empleado(
        String nombre,
        String apellido,
        String trabajo
        ) {
		this.nombre = nombre;
        this.apellido = apellido;
        this.trabajo = trabajo;
	}

	public Empleado() {}



	public Long getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

    public String getApellido() {
		return this.apellido;
	}

	public String getTrabajo() {
		return this.trabajo;
	}


    public void setId(Long id){
		this.id = id;
	}
    
    public void setNombre(String nuevoNombre) {
        this.nombre=nuevoNombre;
	}

    public void setApellido(String nuevoApellido) {
        this.apellido=nuevoApellido;
	}

	public void setTrabajo(String nuevoTrabajo){
		this.trabajo=nuevoTrabajo;
	}

    public void setLegajoEmpleado(Legajo legajoEmpleado) {
        this.legajoEmpleado = legajoEmpleado;
    }

    public Legajo getLegajoEmpleado() {
        return legajoEmpleado;
    }

}
