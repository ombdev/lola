package com.immortalcrab.as400.request;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.javatuples.Pair;

abstract class CfdiRequest {

    protected List<Pair<String, String>> kvs = null;

    abstract Map<String, Object> craftImpt() throws CfdiRequestError;

    abstract void captureSymbolImpt(final String label, final Object value);

    public CfdiRequest(final List<Pair<String, String>> kvs) throws CfdiRequestError {

        this.kvs = kvs;
    }

    protected String bruteSearchUniqueAttr(final String label) throws CfdiRequestError {

        for (Iterator<Pair<String, String>> it = this.kvs.iterator(); it.hasNext();) {

            Pair<String, String> p = it.next();
            if (p.getValue0().equals(label)) {
                return p.getValue1();
            }
        }

        throw new CfdiRequestError("Unique attr " + label + " not found");
    }

    protected Map<String, Object> craft() throws CfdiRequestError {

        captureControlSymbols();
        captureEmisorSymbols();
        captureReceptorSymbols();
        return this.craftImpt();
    }

    protected void captureSymbol(final String label) throws CfdiRequestError {
        this.captureSymbolImpt(label, bruteSearchUniqueAttr(label));
    }

    protected void captureControlSymbols() throws CfdiRequestError {

        // "CFDI_CERT_NO"
        captureSymbol("CDIGITAL");

        // "CFDI_FOLIO"
        captureSymbol("FOLIO");

        // "CFDI_SERIE"
        captureSymbol("SERIE");

        // "CFDI_DATE"
        captureSymbol("FECHOR");
    }

    protected void captureEmisorSymbols() throws CfdiRequestError {

        // "EMISOR_NOMBRE"
        captureSymbol("EMINOM");

        // "EMISOR_RFC"
        captureSymbol("EMIRFC");

        // "EMISOR_CP"
        captureSymbol("EMIZIP");

        // "EMISOR_REG"
        captureSymbol("REGIMEN");
    }

    protected void captureReceptorSymbols() throws CfdiRequestError {

        // "RECEPTOR_NOMBRE"
        captureSymbol("CTENOM");

        // "RECEPTOR_RFC"
        captureSymbol("CTERFC");

        captureSymbol("CTEMAIL");

        captureSymbol("CTEDIR");

        captureSymbol("CTEZIP");
    }
}
