(ns movies.core)

(def movies #{{:title "Citizen Kane" :year 1941}
              {:title "Birth of a Nation" :year 1915}})

(defn -main
  []
  (println (:title (first (sort-by :year movies)))))
