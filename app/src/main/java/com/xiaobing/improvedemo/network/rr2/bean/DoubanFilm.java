package com.xiaobing.improvedemo.network.rr2.bean;

import java.util.List;

public class DoubanFilm {


    /**
     * rating : {"max":10,"average":6.4,"stars":"35","min":0}
     * genres : ["动作","科幻"]
     * title : 金刚狼2
     * casts : [{"alt":"https://movie.douban.com/celebrity/1010508/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22036.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22036.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22036.webp"},"name":"休·杰克曼","id":"1010508"},{"alt":"https://movie.douban.com/celebrity/1327626/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1381837290.77.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1381837290.77.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1381837290.77.webp"},"name":"冈本多绪","id":"1327626"},{"alt":"https://movie.douban.com/celebrity/1326320/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1358575121.5.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1358575121.5.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1358575121.5.webp"},"name":"福岛莉拉","id":"1326320"}]
     * collect_count : 196212
     * original_title : The Wolverine
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1036395/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p11282.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p11282.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p11282.webp"},"name":"詹姆斯·曼高德","id":"1036395"}]
     * year : 2013
     * images : {"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2151837713.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2151837713.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2151837713.webp"}
     * alt : https://movie.douban.com/subject/3718424/
     * id : 3718424
     */

    private RatingBean rating;
    private String title;
    private int collect_count;
    private String original_title;
    private String subtype;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<DirectorsBean> directors;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 6.4
         * stars : 35
         * min : 0
         */

        private int max;
        private double average;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2151837713.webp
         * large : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2151837713.webp
         * medium : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2151837713.webp
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class CastsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1010508/
         * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22036.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22036.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22036.webp"}
         * name : 休·杰克曼
         * id : 1010508
         */

        private String alt;
        private AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBean {
            /**
             * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22036.webp
             * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22036.webp
             * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22036.webp
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class DirectorsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1036395/
         * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p11282.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p11282.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p11282.webp"}
         * name : 詹姆斯·曼高德
         * id : 1036395
         */

        private String alt;
        private AvatarsBeanX avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBeanX {
            /**
             * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p11282.webp
             * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p11282.webp
             * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p11282.webp
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }
}
