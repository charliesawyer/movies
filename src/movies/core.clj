(ns movies.core
  (:import [java.io BufferedReader FileReader]))

(defn get-records
  [file]
  (let [re #"^ *(.+) +\((\d+)\) *$"]
    (letfn [(make [line]
              (let [[_ title year] (re-find re line)]
                {:title title :year year}))]
      (map make (line-seq (clojure.java.io/reader file))))))

(nth (get-records "TITLES.TXT") 99)
