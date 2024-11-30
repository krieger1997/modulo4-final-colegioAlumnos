package org.example.service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PromedioServiceImpTest {

    PromedioServicioImp ps = new PromedioServicioImp();

    @Test
    public void testPromedioConNumerosPositivos() {
        // Nuevos valores para probar
        List<Float> notas = Arrays.asList(7.0f, 8.5f, 9.0f);
        Float resultado = ps.calcularPromedio(notas);
        assertEquals(8.166667f, resultado, 0.000001f);
    }

    @Test
    public void testPromedioConUnSoloElemento() {

        List<Float> notas = Collections.singletonList(9.5f);
        Float resultado = ps.calcularPromedio(notas);
        assertEquals(9.5f, resultado,0.000001f);
    }

    @Test
    public void testPromedioConNumerosNegativos() {

        List<Float> notas = Arrays.asList(-2.0f, -3.0f, -4.0f);
        Float resultado = ps.calcularPromedio(notas);
        assertEquals(-3f, resultado,0.000001f);
    }

    @Test
    public void testPromedioConListaVacia() {

        List<Float> notas = Collections.emptyList();
        Exception exception = assertThrows(ArithmeticException.class, () -> ps.calcularPromedio(notas));
        assertEquals("La lista de notas está vacía", exception.getMessage());
    }

    @Test
    public void testPromedioConNumerosMixtos() {

        List<Float> notas = Arrays.asList(-3.0f, 0.0f, 3.0f);
        Float resultado = ps.calcularPromedio(notas);
        assertEquals(0f, resultado,0.000001f);
    }
}
