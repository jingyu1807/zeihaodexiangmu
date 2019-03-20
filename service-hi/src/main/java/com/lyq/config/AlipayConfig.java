package com.lyq.config;

public class AlipayConfig {

    //开发者中心 / 研发服务 / 沙箱环境 / 沙箱应用/ 信息配置/ 必看部分/ APPID（填自己的，下面都是改过的~）
    public static String app_id = "2016092500593185";

    //开发者中心 / 研发服务 / 沙箱环境 / 沙箱应用/ 信息配置/ 必看部分/ 生成公钥时对应的私钥（填自己的，下面都是改过的~）
    public static String private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCXNc9uBJ4cYr+mLYV3c28cDMvM8wtDJgdh21aE7Hj9F4G6LJOz1ycY7yzcveUNHBWXmMj080wrUX1nNLm789QNUQ1iHhEgY9df8Qn2Jmx11qTh8GDV8VH5U1gp4NzWd8AIo1oWxoaRtnehqdZpgjp0rmUeYM1YmzM8WKVNAav170NG4yd/sBEehJboIgCCqg+bTGqALCsZcct8hviRsS2hbGBRu4B1NLU3Hou6QAuK10D+MjVITYJAYaIlxqFB6UfIHwkysBoM5w/afmYezBrDm/6IP31PAK+lpbrkpnyq+p+1g8WTQr57RcNxgSZvMMWo+qd1ACmstcqu8Ep2E3jLAgMBAAECggEATRZmyJzcCnahY9PDPjGATJmNcM/bRwFlNBNBGYUA7W6FeWduaXnfecu9YFADLNrLezyt/ormoxLpTqtHoTVePue6XfIcIxojW8sjm/TcyHqEFpeYlplvhB9kS/1SOJhWM1EsBX5ybmjKtoVCRQnaSU/7RzBsn6aHyVUb+JVImJjz0A3mjB7x+JgAjw77NgjwviXFlArEBtG79sLtkTZdn8qDCIll3v/DeGJQYk+IVqf0sSiCT6M1D5JDJOfRbrULX+YM9vf+/8JQBE8Od/bRqi1mbe6cnJF8Zw7UHCccof4ipzAD0OWVZfBuI8JyMztHqtlCKyN/TjgoCn7kH7B7gQKBgQDzhdrxvK0LfM3w/4paseTV0TpARlya4ftKsY+w2DTZdW9TaXWJvCyQ6ia4In916MXKZbXYaoRF8/sJZnnagdrPy6LE+WPMHq/ExbcAXIdisamRnu0H3s1xWl8O28Gdrarl1LT12qbu0bgXwehzB0tEtZPuRpfD7DMxJkBZf4lGQQKBgQCe9ST/7108RC6NRjg8kXENFH1Ml4v/fVIOUQn9FiT/NIAFVjTb1VDKajVe5LwJLznHYHVo20Lh0V4Afc/Tv7VD7hmtvyvg4n2SfNSOG4m0RQubNBysiRAmowdjmNFvD191jWSBupMip5nSfE8q4GNDaE2fPesmCxzp1AeZYVh0CwKBgGcM7MLBGeUzkh56tLx+bRzCJklOvR9Zo7JPA0RNqcef91Uv2mpHtAA65dmj15Y4w4JLmjETmhfUEBZBRC1gKA0o2h9KfZ3CGpVFKZTAkxYDooMZ4qoi8YMXQASUoBEujZQmd9FWDrAfFS0QhGIw4vwXbps1SkHBcURKMI7u9lNBAoGAVg2SfTeBOvoSVYrZMPjvwcwA1TEJqkOW44c9qBSP7NToJXt43DXMKfh7cibUIQi+98+hYCWrPILSp+N0Wkbjg6XnoubmgzU6E86eZ1xDHSNv2BLLHyCIbNI/UszNPJmO7R2hdpWhx9YB8TA4z5ZwRnK16upjXdIQUgnmH0PS1ecCgYB1Uz+/zXYx6NFWQlk6jQMn1XFm6NKVrM1H9IFXjKw5yWp+xRu9GUASn/XdyW8ldC5zcHouV8aUOcCOC4ANKe0AqHsiZGv8SM0xOkTyErN+ZyEt8nIxOwppww1YxNhXYH7J2NZ9dtjjf7YkQCC1tZKFvuJvC1mslok41Pp/Opt2VA==";
    //Controller Mapping:得放到服务器上，且使用域名解析 IP
    public static String notify_url = "http://www.linhongcun.com/SpringBootPay-0.0.1-SNAPSHOT/pay/notifyUrl";

    //Controller Mapping:得放到服务器上，且使用域名解析 IP
    public static String return_url = "http://127.0.0.1:8081/toindex";

    //开发者中心 / 研发服务 / 沙箱环境 / 沙箱应用/ 信息配置/ 必看部分/ 支付宝网关（注意沙箱alipaydev，正式则为 alipay）
    public static String url = "https://openapi.alipaydev.com/gateway.do";

    public static String charset = "UTF-8";

    public static String format = "json";

    //开发者中心 / 研发服务 / 沙箱环境 / 沙箱应用/ 信息配置/ 必看部分/ 公钥（填自己的，下面都是改过的~）
    public static String public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlzXPbgSeHGK/pi2Fd3NvHAzLzPMLQyYHYdtWhOx4/ReBuiyTs9cnGO8s3L3lDRwVl5jI9PNMK1F9ZzS5u/PUDVENYh4RIGPXX/EJ9iZsddak4fBg1fFR+VNYKeDc1nfACKNaFsaGkbZ3oanWaYI6dK5lHmDNWJszPFilTQGr9e9DRuMnf7ARHoSW6CIAgqoPm0xqgCwrGXHLfIb4kbEtoWxgUbuAdTS1Nx6LukALitdA/jI1SE2CQGGiJcahQelHyB8JMrAaDOcP2n5mHswaw5v+iD99TwCvpaW65KZ8qvqftYPFk0K+e0XDcYEmbzDFqPqndQAprLXKrvBKdhN4ywIDAQAB";

    public static String signtype = "RSA2";











}
