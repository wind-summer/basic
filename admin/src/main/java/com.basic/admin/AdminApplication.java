package com.basic.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.basic.core","com.basic.admin"})
@MapperScan("com.basic.core.module.**.dao")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        //ConfigurableApplicationContext context = SpringApplication.run(AdminApplication.class, args);
        //装载监听
        //context.addApplicationListener(new MyListener());
    }

//    @Bean
//    public CommandLineRunner testGzh(DemoService demoService) {
//        return args -> {
//            /*Set<Gzh> gzhSet = new HashSet<>();*/
//            Demo demo = Demo.builder().age(1).name("asdf").build();
//
//            /*for (int i = 1; i < 21; i++) {
//                Bundle bundle = Bundle.builder().bundleCode("H" + RandomUtil.randomString(14)).amount(100000).bankName("工商银行").cleanBank("招商银行")
//                        .status(BundleStatus.NORMAL.getValue()).flag(BundleFlag.HANDFUL.getValue())
//                        .createdDate(LocalDateTime.now()).build();
//                IntStream.range(1, 101).forEach(value -> {
//                    Gzh gzh = Gzh.builder().gzhNo("G1000" + RandomUtil.randomString(5)).charNum(10).moneyFlag("CNY").tfFlag(1).valuta(100).ver(2)
//                            .createdDate(LocalDateTime.now()).build();
//                    gzhSet.add(gzh);
//                });
//                gzhSearchService.indexBundleGzh(bundle, gzhSet);
//            }*/
//            //System.out.println(gzhSearchService.getBundleByCode("B00000006"));
////            gzhSearchService.findByGzhNoPosition("G00-4-5",PageRequest.of(0,100))
////                    .forEach(gzh -> System.out.println(gzh));
////            gzhSearchService.findBundleByGzh("G000410").forEach(b -> System.out.println(b.toString()));
//
//        };
//    }
}
