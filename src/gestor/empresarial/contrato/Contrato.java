package gestor.empresarial.contrato;

import java.util.ArrayList;
import java.util.List;

public final class Contrato {
    private int noContrato;
    private int annio;
    private String horario;
    private Cargos tipoCargo;

    public Contrato(int noContrato, int annio, String horario, Cargos tipoCargo){
        this.noContrato = noContrato;
        this.annio = annio;
        this.horario = horario;
        this.tipoCargo = tipoCargo;
    }

    public int getNoContrato() {
        return noContrato;
    }

    public int getAnnio() {
        return annio;
    }

    public String getHorario() {
        return horario;
    }

    public Cargos getTipoCargo() {
        return tipoCargo;
    }

    public void setTipoCargo(Cargos tipoCargo) {
        this.tipoCargo = tipoCargo;
    }
}
