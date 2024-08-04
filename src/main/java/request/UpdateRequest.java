package request;

import api.Limit;

public class UpdateRequest extends Request{

    private Limit limit;
    public UpdateRequest(String serviceName, String id, Limit limit) {
        super(serviceName, id);
        this.limit = limit;
    }
}
