(ns movies.core
  (:import [java.io BufferedReader FileReader]))

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

(defn movies-to-db
  "A map of year to vector of movies made that year from MOVIE-STREAM."
  [movie-stream]
  (letfn [(add [prior m] (conj (or prior []) m))]
    (loop [result {} movies movie-stream]
      (if (empty? movies) result
          (let [m (first movies) db (update-in db [(:year m)] add m)]
            (recur db (rest movies)))))))

(def db (movies-to-db my-vids))

(defn movies-from-year
  [db year]
  "Return sequence of movie titles by year"
  (get db year))

(clojure.pprint/pprint (movies-from-year db "1989"))
