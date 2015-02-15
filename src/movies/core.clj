(ns movies.core)

(defn make-year-to-movies-db
  "A map of year to vector of movies from that year."
  [file]
  (letfn [(make [line]
            (let [[_ t y] (re-find #"^ *(.+) +\((\d+)\) *$" line)]
              {:title t :year (Integer/parseInt y)}))
          (add [db movie]
            (update-in db [(:year movie)] (fnil conj []) movie))]
    (reduce add {} (map make (line-seq (clojure.java.io/reader file))))))

(def db (make-year-to-movies-db "TITLES.TXT"))

(clojure.pprint/pprint (map :title (get db 1989)))
