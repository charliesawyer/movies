(ns movies.core
  (:import [java.io BufferedReader FileReader]))

(defn make-video-stream [x] (line-seq (new java.io.BufferedReader (new java.io.FileReader x ))))

(defn make-movie-stream
  "A lazy seqence of lines from file."
  [file]
  (line-seq (new java.io.BufferedReader
                 (new java.io.FileReader file))))

(defn make-movie-array
  "make an array from string of form 'Title (Year)'"
  [x]
  (clojure.string/split x #"[\(\)]"))

(defn make-movie-map
  "from [TITLE YEAR] make {:title TITLE :year YEAR}"
  [line]
  {:title (get line 0) :year (get line 1)})

(defn make-movies
  "A sequence of movie maps from file. "
  [file]
  (let [vids (make-movie-stream file)]
    (map make-movie-map (map make-movie-array vids))))

(def my-vids
  "The movie maps from TITLES.TXT."
  (make-movies "TITLES.TXT"))

(nth my-vids 99)

(defn movies-from-year
  [movies year]
  "Return sequence of movie titles by year"
  (letfn [(pred? [m] (= year (:year m)))]
    (map :title (filter pred? movies))))

(movies-from-year my-vids "1941")
