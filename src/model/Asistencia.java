package model;

import java.io.Serializable;
import java.sql.Date;

public class Asistencia implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idPersona;
	private Date horaEntrada;
	private Date horaSortida;


	public Asistencia(int idPersona, Date horaEntrada, Date horaSortida) {
		super();
		this.idPersona = idPersona;
		this.horaEntrada = horaEntrada;
		this.horaSortida = horaSortida;
	}


	public int getIdPersona() {
		return idPersona;
	}


	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}


	public Date getHoraEntrada() {
		return horaEntrada;
	}


	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}


	public Date getHoraSortida() {
		return horaSortida;
	}


	public void setHoraSortida(Date horaSortida) {
		this.horaSortida = horaSortida;
	}


	@Override
	public String toString() {
		return "Asistencia [idPersona=" + idPersona + ", horaEntrada=" + horaEntrada + ", horaSortida=" + horaSortida
				+ "]";
	}


	public void imprimir(){
		System.out.println(this.toString());
	}
}
