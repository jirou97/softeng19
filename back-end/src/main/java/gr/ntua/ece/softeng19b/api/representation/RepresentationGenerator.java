package gr.ntua.ece.softeng19b.api.representation;

import gr.ntua.ece.softeng19b.data.model.*;


import org.restlet.representation.Representation;

import java.util.List;

public interface RepresentationGenerator {

    public Representation generateRepresentation(List<ATLRecordForSpecificDay> result);
    public Representation generateRepresentation1(List<ATLRecordForSpecificMonth> result);
    public Representation generateRepresentation2(List<ATLRecordForSpecificYear> result);
    public Representation generateRepresentation3(List<DATLFRecordForSpecificDay> result);
    public Representation generateRepresentation4(List<DATLFRecordForSpecificMonth> result);
    public Representation generateRepresentation5(List<DATLFRecordForSpecificYear> result);
    public Representation generateRepresentation6(List<AGPTRecordForSpecificDay> result);
    public Representation generateRepresentation7(List<AGPTRecordForSpecificMonth> result);
    public Representation generateRepresentation8(List<AGPTRecordForSpecificYear> result);
    public Representation generateRepresentation9(List<AvFRecordForSpecificDay> result);
    public Representation generateRepresentation10(List<AvFRecordForSpecificMonth> result);
    public Representation generateRepresentation11(List<AvFRecordForSpecificYear> result);

}
