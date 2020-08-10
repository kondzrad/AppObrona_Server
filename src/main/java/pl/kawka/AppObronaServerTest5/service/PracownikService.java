package pl.kawka.AppObronaServerTest5.service;

import pl.kawka.AppObronaServerTest5.model.PracownikSQL;

import java.util.List;

public interface PracownikService {

    List<PracownikSQL> getPracownikSQL();

    String getLogowanie(PracownikSQL pracownikSQLaaa2);

}
