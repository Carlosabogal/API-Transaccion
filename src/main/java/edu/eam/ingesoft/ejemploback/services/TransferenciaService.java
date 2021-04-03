package edu.eam.ingesoft.ejemploback.services;

import edu.eam.ingesoft.ejemploback.model.Cliente;
import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.model.Transferencia;
import edu.eam.ingesoft.ejemploback.repositories.ClienteRepository;
import edu.eam.ingesoft.ejemploback.repositories.CuentaRepository;
import edu.eam.ingesoft.ejemploback.repositories.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransferenciaService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private TransferenciaRepository transferenciaRepository;

   //consignar dinero en la cuenta. guardar la transaccion
   public  void consignarCuenta(String id,double ammount) {
       Cuenta cuenta = cuentaRepository.getOne(id);
       double monto=cuenta.getAmount();
       double suma=ammount+monto;
       cuenta.setAmount(suma);
       cuentaRepository.save(cuenta);
       java.util.Date fecha = new Date();

       Transferencia transferencia = new Transferencia(cuenta.getId(),"consignacion",ammount,fecha);
       transferenciaRepository.save(transferencia);

   }
   //retirar dinero de la cuenta. guardar la transaccion
   public String retirarCuenta(String id,double ammount) {
       Cuenta cuenta = cuentaRepository.getOne(id);
       double monto=cuenta.getAmount();
       if(monto>=ammount){
           double resta=monto-ammount;
           cuenta.setAmount(resta);
           cuentaRepository.save(cuenta);
       }
           else{
            return "no hay dinero que guardar";

           }
       java.util.Date fecha = new Date();

       Transferencia transferencia = new Transferencia(cuenta.getId(),"retiro",ammount,fecha);
       transferenciaRepository.save(transferencia);
       return "Retiro realizado con exito";

   }
   ///
    public String realizarTransferencia(String idCuenta1,String idCuenta2,double ammount) {
        if (!idCuenta1.equals(idCuenta2)) {

            String resultado = retirarCuenta(idCuenta1, ammount);
            if (!resultado.equals("no hay dinero que guardar")) {
                consignarCuenta(idCuenta2, ammount);
                return "Transaccion exitosa";
            }

            return "Transaccion fallida";
        }
        return "No se puede realizar una transaccion a la misma cuenta";

    }


    //cancelar cuenta: solo si el saldo es cero.
    public  String cancelarCuenta(String id) {
        Cuenta cuenta = cuentaRepository.getOne(id);
        if(cuenta.getAmount()==0){

            cuentaRepository.deleteById(id);
            return "Cuenta eliminada con exito";
        }
            return "La cuenta indicada tiene  un saldo superior a 0";

    }

    /**
    //eliminar cliente, solo si no tiene cuentas.
    public void eliminarCliente(String id) {
        Cliente cliente = clienteRepository.getOne(id);
        int cuentas=cliente.getCuentas().size();

        if (cuentas == 0) {
            clienteRepository.deleteById(id);
        }
    }

     **/


    //eliminar cliente, solo si no tiene cuentas.
    public String eliminarCliente(String cedula) {
        List<Cuenta> cuentasCliente = cuentaRepository.buscarCuentasCliente(cedula);

        int cuentas=cuentasCliente.size();
        if (cuentas == 0) {
            clienteRepository.deleteById(cedula);
            return "Cliente eliminado con exito";

        }
        return "No se puede eliminar el cliente";
    }

    //consultar las transacciones de una cuenta.

    public List<Transferencia> listaTransaciones(String numeroCuenta){
       return transferenciaRepository.buscarTransacionesCuentas(numeroCuenta);
    }


}
