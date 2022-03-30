package botiga;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Locale.Category;

public class Presencia {
    static Locale localeFormat = Locale.getDefault(Category.FORMAT);
    static DateTimeFormatter datePattern = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(localeFormat);
    private Integer idtreballador;
    private LocalDate data;
    private LocalTime horaEntrada;
    private LocalTime horaSortida;
    
    public Presencia(Integer idtreballador, LocalDate data, LocalTime horaEntrada, LocalTime horaSortida) {
        this.idtreballador = idtreballador;
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.horaSortida = horaSortida;
    }

    public Presencia(Integer idtreballador, LocalDate data, LocalTime horaEntrada) {
        this.idtreballador = idtreballador;
        this.data = data;
        this.horaEntrada = horaEntrada;
    }

    public Presencia(Integer idtreballador, LocalDate data) {
        this.idtreballador = idtreballador;
        this.data = data;
    }

    public Integer getIdtreballador() {
        return idtreballador;
    }
    public LocalDate getData() {
        return data;
    }
    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }
    public LocalTime getHoraSortida() {
        return horaSortida;
    }
    public void setHoraSortida(LocalTime horaSortida) {
        this.horaSortida = horaSortida;
    }

    @Override
    public String toString() {
        return "Presencia [data=" + datePattern.format(data) + ", horaEntrada=" + horaEntrada + ", horaSortida=" + horaSortida
                + ", idtreballador=" + idtreballador + "]";
    }
}
