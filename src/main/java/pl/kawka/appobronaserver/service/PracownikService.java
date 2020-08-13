package pl.kawka.appobronaserver.service;

import pl.kawka.appobronaserver.model.PracownikSQL;

import java.util.List;

public interface PracownikService {

    List<PracownikSQL> getPracownikSQL();

    String getLogowanie(PracownikSQL pracownikSQLaaa2);

}
