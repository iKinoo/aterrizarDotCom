package com.aterrizar.service.checkin.flow;

import org.springframework.stereotype.Service;

import com.aterrizar.service.checkin.steps.CompleteCheckinStep;
import com.aterrizar.service.checkin.steps.GetSessionStep;
import com.aterrizar.service.checkin.steps.PassportInformationStep;
import com.aterrizar.service.checkin.steps.RfcInputStep;
import com.aterrizar.service.checkin.steps.SaveSessionStep;
import com.aterrizar.service.checkin.steps.TaxAgreementStep;
import com.aterrizar.service.checkin.steps.TaxCalculationStep;
import com.aterrizar.service.checkin.steps.ValidateSessionStep;
import com.aterrizar.service.core.framework.flow.FlowExecutor;
import com.aterrizar.service.core.framework.flow.FlowStrategy;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MxContinueFlow implements FlowStrategy {
    private final GetSessionStep getSessionStep;
    private final ValidateSessionStep validateSessionStep;
    private final RfcInputStep rfcInputStep;
    private final TaxCalculationStep taxCalculationStep;
    private final TaxAgreementStep taxAgreementStep;
    private final PassportInformationStep passportInformationStep;
    private final CompleteCheckinStep completeCheckinStep;
    private final SaveSessionStep saveSessionStep;

    @Override
    public FlowExecutor flow(FlowExecutor baseExecutor) {
        return baseExecutor
                .and(getSessionStep)
                .and(validateSessionStep)
                .and(rfcInputStep)
                .and(taxCalculationStep)
                .and(taxAgreementStep)
                .and(passportInformationStep)
                .and(completeCheckinStep)
                .andFinally(saveSessionStep);
    }
}
