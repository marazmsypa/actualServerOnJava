package org.example.data.extendedModel;

import lombok.*;
import org.example.data.model.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedRequests {
    private Integer id;
    private RequestTypes request_type;
    private RequestStatuses request_status;

    private Date date_start;
    private Date date_end;
    private VisitPurposes visit_purpose;

    private Employees employee;

    private Integer group_id;

    private Visitors visitor;

    private boolean is_group;

    private String message;
}
