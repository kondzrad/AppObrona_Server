package pl.kawka.AppObronaServerTest5.dao;

import pl.kawka.AppObronaServerTest5.model.PracownikSQL;

import java.util.List;

public interface PracownikDAO {

    List<PracownikSQL> getPracownikSQL();

    String getLogowanie(PracownikSQL pracownikSQL);
}
