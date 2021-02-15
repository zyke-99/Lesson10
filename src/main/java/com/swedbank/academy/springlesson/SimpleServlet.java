package com.swedbank.academy.springlesson;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class SimpleServlet extends SpringBootServletInitializer {

        /*
         * (non-Javadoc)
         *
         * @see
         * org.springframework.boot.web.servlet.support.SpringBootServletInitializer#
         * configure(org.springframework.boot.builder.SpringApplicationBuilder)
         */
        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(SpringLessonApplication.class);
        }
        /**
         * The Class ServletInitializerContoller.
         */
        @RestController
        static class ServletInitializerContoller {
            /**
             * Handler.
             *
             * @return the string
             */
            @GetMapping("/ping")
            public String ping() {
                return "This is your application";
            }
        }

}
