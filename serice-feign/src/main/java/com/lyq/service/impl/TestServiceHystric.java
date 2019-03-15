package com.lyq.service.impl;

        import com.lyq.service.TestService;
        import org.springframework.stereotype.Component;

        import java.util.List;

@Component
public class TestServiceHystric implements TestService {
    @Override
    public String hi(String name) {
        return "hi,"+name+",sorry,error!feign";
    }

    @Override
    public List queryCatalog() {
        return null;
    }
}
