package edu.eam.ingesoft.ejemploback.controllers;

import edu.eam.ingesoft.ejemploback.model.Transferencia;
import edu.eam.ingesoft.ejemploback.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransferenciaController {
    @Autowired
    private TransferenciaService transferenciaService;

    //1
    @PutMapping("/accounts/consignar/{id}/{ammount}")
    public ResponseEntity<Void> consignarCuenta(@PathVariable String id , @PathVariable double ammount){
        transferenciaService.consignarCuenta(id, ammount);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/accounts/retirar/{id}/{ammount}")
    public  ResponseEntity<String > retirarCuenta(@PathVariable String id,@PathVariable double ammount){
        String resultado = transferenciaService.retirarCuenta(id, ammount);
        return  ResponseEntity.ok(resultado);
    }

    @PutMapping("/accounts/transferir/{id1}/{id2}/{ammount}")
    public ResponseEntity<String> realizarTransferencia(@PathVariable String id1,@PathVariable String id2,@PathVariable double ammount ){
        String resultado = transferenciaService.realizarTransferencia(id1,id2,ammount);
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/accounts/delete/{id}")
    public ResponseEntity<String> cancelarCuenta(@PathVariable String id){
        String resultado=transferenciaService.cancelarCuenta(id);
        return  ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/customers/delete/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable String id){
        String resultado = transferenciaService.eliminarCliente(id);
        return ResponseEntity.ok(resultado);

    }

    @GetMapping("/customers/account/{numeroCuenta}")
    public ResponseEntity<List<Transferencia>> listaTransaciones(@PathVariable String numeroCuenta) {
        List<Transferencia> transferencias = transferenciaService.listaTransaciones(numeroCuenta);
        return ResponseEntity.ok(transferencias);
    }


}
