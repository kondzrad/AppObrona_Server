package pl.kawka.appobronaserver.dao;

import pl.kawka.appobronaserver.model.PracownikSQL;

import java.util.List;

public interface PracownikDAO {

    List<PracownikSQL> getPracownikSQL();

    String getLogowanie(PracownikSQL pracownikSQL);
}
