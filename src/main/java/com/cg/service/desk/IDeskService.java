package com.cg.service.desk;

import com.cg.model.Desk;
import com.cg.service.IGeneralService;

public interface IDeskService extends IGeneralService<Desk, Long> {
    public void softDelete(Desk desk);

}
