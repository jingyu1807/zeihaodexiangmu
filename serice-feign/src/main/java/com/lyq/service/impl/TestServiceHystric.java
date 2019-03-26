package com.lyq.service.impl;

        import com.lyq.model.SitesUser;
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

        @Override
        public List queryPay() {
            return null;
        }

        @Override
    public SitesUser login(SitesUser name) {
        return null;
    }

        @Override
        public String regist(SitesUser user) {
            return null;
        }

        @Override
        public void alipay(String money) {

        }

        @Override
        public List queryPa() {
            return null;
        }

        @Override
        public void sendMessage() {

        }

        @Override
<<<<<<< HEAD
        public SitesUser queryBalance(Integer id) {
            return null;
        }

        @Override
        public void updateMem(Integer ids) {

        }


=======
        public List ke() {
            return null;
        }
>>>>>>> origin/master
    }
