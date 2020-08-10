package pl.kawka.AppObronaServerTest5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kawka.AppObronaServerTest5.dao.PracownikDAO;
import pl.kawka.AppObronaServerTest5.model.PracownikSQL;

import java.util.List;

@Service
public class PracownikServiceImplementacja implements PracownikService {

    @Autowired
    private PracownikDAO pracownikDAO;

    @Transactional
    @Override
    public List<PracownikSQL> getPracownikSQL() {
        return pracownikDAO.getPracownikSQL();
    }

    @Override
    public String getLogowanie(PracownikSQL pracownikSQLaaa2) {
        return pracownikDAO.getLogowanie(pracownikSQLaaa2);
    }

}
