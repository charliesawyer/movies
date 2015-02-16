(ns movies.core)

(defn make-year-to-movies-db
  "A map of year to vector of movies from that year."
  [file]
  (letfn [(make [line]
            (let [[m t y] (re-find #"^ *(.+) +\((\d+)\) *$" line)]
              (if (and m t y)
                {:title t :year (Integer/parseInt y)}
                (throw (Error. (str "WTF?: " (pr-str line))) ))))]
    (group-by :year (map make (line-seq (clojure.java.io/reader file))))))

(def db (make-year-to-movies-db "TITLES.TXT"))

(clojure.pprint/pprint (map :title (get db 1989)))
