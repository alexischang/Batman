package gametest9th.utils;

import java.util.logging.FileHandler;

public class Path {

    public static abstract class Flow {
        private String path;

        public Flow(String path) {
            this.path = path;
        }

        public Flow(Flow flow, String path) {
            this.path = flow.path + path;
        }

        @Override
        public String toString() {
            return path;
        }
    }


    private static class Resources extends Flow{
           private Resources() {
               super("/resources");
           }
    }

    public static class Imgs extends Flow {
        private Imgs() {
            super(new Resources(), "/imgs");
        }

        public static class Actors extends Flow {

            private Actors(Flow flow) {
                super(flow, "/actors");
            }

            public String airCraft() {

                return this + "/airplane1.png";
            }

            public String enemy() {

                return this + "/enemy1.png";
            }

            public String actor() {

                return this + "/actor.jpeg";
            }
            public String batman() {

                return this + "/batman.png";
            }
            public String joker() {

                return this + "/joker.png";
            }
        }

        public static class Objs extends  Flow {
            private Objs(Flow flow) {
                super(flow, "/objs");
            }

            public String boom() {
                return this + "/boom.png";
            }

            public String boom2() {
                return this + "/boom2.png";
            }

            public String batmanDart() {
                return this + "/batmanDart.png";
            }

        }

        public static class Backgrounds extends  Flow {
            private Backgrounds(Flow flow) {
                super(flow, "/backgrounds");
            }
            public String sea() {
                return this + "/sea.png";
            }
            public String cloud() {
                return this + "/cloud.png";
            }
        }

        public Actors actors() {
            return new Actors(this);
        }
        public Objs objs() {
            return new Objs(this);
        }
        public Backgrounds backgrounds() {
            return new Backgrounds(this);
        }

    }

    public static class Sounds extends Flow {
        private Sounds() {
            super(new Resources(), "/soundss");
        }


            public String alarm() {

                return this + "/Alarm01.wav";
            }


    }


    public Imgs img() {
        return new Imgs();
    }
    public Sounds sound() {
        return new Sounds();
    }




}
