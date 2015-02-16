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

{"1941" [{:title "Citizen Kane" :year "1941"}
         {:title "Suspense" :year "1941"}]
 "1970" [{:title "Jaws" :year "1970"}]}

(defn make-movie-database
  "Turn stream of movies ms into a database index by year"
  [ms]
  (let [movie (first ms)]
    {(:year movie) [movie]}))

(make-movie-database my-vids)
(conj [{:title "Citizen Kane" :year "1941"}
       {:title "Suspense" :year "1941"}]
      {:title "Jaws" :year "1970"})
(def mini-db {"1941" [{:title "Citizen Kane" :year "1941"}
         {:title "Suspense" :year "1941"}]
 "1970" [{:title "Jaws" :year "1970"}]}
)
(conj {:foo 1 :bar 2} {:foo 3 :hoodoo "foo"})
(def x {:foo [1 2 3] :bar "bar"})
(update-in x [:foo] conj 4)
(def psycho {:year "1970" :title "Psycho"})
(update-in mini-db ["1970"] conj psycho)
(get mini-db "1941")

(defn make-empty-movie-db []
  (loop [iteration 2010 ray {}]
    (if (> iteration 2015)
      ray
      (recur (inc iteration) (conj ray {(str iteration) []})))))
(make-empty-movie-db)
(def movie-db (make-empty-movie-db))
movie-db

(defn add-movie-to-db-entry [db movie]
  "Add movie to array for movie's year"
  (let [year (:year movie)]
     (conj (or (get db year) []) movie)))

(defn add-movies-to-db
  [db movie-stream]
  (comment "How do you use add-movie-to-db-entry here?"))

(def mv1 {:year "1941" :title "Citizen Kane"})
(add-movie-to-db {} mv) ;; Should return [mv1], returns {}
(def mv2 {:year "1941" :title "Suspense"})
(add-movie-to-db (add-movie-to-db {} mv1) mv2) ;; should return [mv1
;; mv2], returns {} instead

