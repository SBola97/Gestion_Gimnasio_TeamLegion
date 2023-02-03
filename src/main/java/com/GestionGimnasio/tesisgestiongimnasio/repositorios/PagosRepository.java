package com.GestionGimnasio.tesisgestiongimnasio.repositorios;

import com.GestionGimnasio.tesisgestiongimnasio.entidades.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PagosRepository extends JpaRepository<Pagos,Integer> {
    @Query(value="SELECT sum(valorp) FROM pagos WHERE MONTH(fecha_pago) =:mes",nativeQuery = true)
    public BigDecimal sumValorpByFechaPago(@Param("mes") int mes);

    @Query(value="SELECT sum(valorp) FROM pagos WHERE DAY(fecha_pago) =:dia",nativeQuery = true)
    public BigDecimal sumValorpByDia(@Param("dia") int dia);

    @Query(value="SELECT pe.nombre,pe.apellidos,pa.fecha_pago, pa.valorp from pagos as pa inner join inscripciones as i" +
            " on i.id_inscripcion = pa.id_inscripcion inner join personas as pe " +
            "on i.id_persona = pe.id_persona where month(pa.fecha_pago) =:mes",nativeQuery = true)
    public List<Object> findPagosByFechaPago(@Param("mes") int mes);


    @Query(value="SELECT pe.nombre, pe.apellidos, i.fecha_inicio, i.fecha_fin, p.valorp FROM pagos as p " +
            "inner join inscripciones as i on p.id_inscripcion = i.id_inscripcion inner join personas as pe " +
            "on i.id_persona = pe.id_persona  WHERE p.estado_pago = 'PENDIENTE' and month(p.fecha_pago) =:mes"
            ,nativeQuery = true)
    public List<Object> findPagosByEstadoPagoAndFechaPago(@Param("mes") int mes);


    @Query(value="SELECT monthname(p.fecha_pago) as mes, sum(p.valorp) from pagos as p " +
            "where year(p.fecha_pago) = year(CURRENT_DATE) group by mes order by month(p.fecha_pago) asc",nativeQuery = true)
    public List<Object> findPagosAnuales();



}
