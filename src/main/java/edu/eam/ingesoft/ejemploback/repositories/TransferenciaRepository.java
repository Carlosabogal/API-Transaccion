package edu.eam.ingesoft.ejemploback.repositories;

import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.model.Transferencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, String> {


    @Query("SELECT o FROM Transferencia o  WHERE o.numerocuenta = :numero")
    List<Transferencia> buscarTransacionesCuentas(String numero);

}
