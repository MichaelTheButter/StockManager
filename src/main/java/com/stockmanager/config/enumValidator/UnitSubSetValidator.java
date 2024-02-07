package com.stockmanager.config.enumValidator;

import com.stockmanager.config.enumValidator.constraints.UnitSubset;
import com.stockmanager.domain.product.unit.Unit;

public class UnitSubSetValidator extends EnumSubSetValidator<UnitSubset, Unit> {
    @Override
    public void initialize(UnitSubset constraint) {
        super.initialize(constraint.anyOf());
    }
}
