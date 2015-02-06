(ns movies.core
  (:import [java.io BufferedReader FileReader]))

(defn make-video-stream
  "A lazy seqence of lines from file."
  [file]
  (line-seq (new java.io.BufferedReader
                 (new java.io.FileReader file))))

(defn make-video-array
  "make an array from string of form 'Title (Year)'"
  [x]
  (clojure.string/split x #"[\(\)]"))

(defn make-video-map
  "from [TITLE YEAR] make {:title TITLE :year YEAR}"
  [line]
  {:title (get line 0) :year (get line 1)})

(defn make-videos
  "A sequence of movie maps from file. "
  [file]
  (let [vids (make-video-stream file)]
    (map make-video-map (map make-video-array vids))))

(def my-vids
  "The movie maps from TITLES.TXT."
  (make-videos "TITLES.TXT"))

(nth my-vids 99)

(defn movies-from-year
  [movies year]
  "Return sequence of movie titles by year"
  (letfn [(pred? [m] (= year (:year m)))]
    (map :title (filter pred? movies))))

(movies-from-year my-vids "1941")
