package com.GestionGimnasio.tesisgestiongimnasio.util.paginacion;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PageRender <T>{

    private String url;

    private Page<T> page;

    private int totalPaginas;

    private int numElementosPag;

    private int paginaActual;

    private List<PageItem> paginas;

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.paginas = new ArrayList<PageItem>();

        numElementosPag = 5;
        totalPaginas = page.getTotalPages();
        paginaActual = page.getNumber() + 1;

        int desde, hasta;
        if (totalPaginas <= numElementosPag) {
            desde = 1;
            hasta = totalPaginas;
        } else {
            if (paginaActual <= numElementosPag / 2) {
                desde = 1;
                hasta = numElementosPag;
            } else if (paginaActual >= totalPaginas - numElementosPag / 2) {
                desde = totalPaginas - numElementosPag + 1;
                hasta = numElementosPag;
            } else {
                desde = paginaActual - numElementosPag / 2;
                hasta = numElementosPag;
            }
        }
        for (int i = 0; i < hasta; i++) {
            paginas.add(new PageItem(desde + i, paginaActual == desde + i));
        }
    }
    public boolean isLast()
    {
        return page.isLast();
    }

    public boolean isFirst(){return page.isFirst();}

    public boolean isHasNext()
    {
        return page.hasNext();
    }

    public boolean isHasPrevious()
    {
        return page.hasPrevious();
    }
}
