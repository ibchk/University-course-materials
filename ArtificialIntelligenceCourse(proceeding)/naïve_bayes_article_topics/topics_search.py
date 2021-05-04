import csv
from math import log


def train():
    """
    method which uses training file to divide articles words and topics

    :return: dict with all words by topic, dict with number of topic articles
    """
    articles_words = {}
    articles_per_topic = {}
    with open("bbc_train.csv", encoding="utf-8") as f:
        rd = csv.reader(f)
        for topic, text in rd:
            # lowercase tokens longer than 3 chars
            words = [w.lower() for w in text.split() if len(w) > 3]
            if topic in articles_words:
                articles_per_topic[topic] += 1
            else:
                articles_per_topic[topic] = 1
                articles_words[topic] = {}
            for word in words:
                if word in articles_words[topic]:
                    articles_words[topic][word] += 1
                else:
                    articles_words[topic][word] = 1
    return articles_words, articles_per_topic


def test():
    """
    method uses test() data to analyse articles to give them topics

    :return: list with results, where you see expected and real topic
    """
    articles_words, articles_per_topic = train()
    results = []
    all_words_in_topic = {}
    for t in articles_words:
        t_nr = 0
        for t2 in articles_words[t]:
            t_nr += articles_words[t][t2]
        all_words_in_topic[t] = t_nr
    with open("bbc_test.csv", encoding="utf-8") as f:
        rd = csv.reader(f)
        for topic, text in rd:
            article_right_topic = ""
            article_right_topic_percent = -100000
            # lowercase tokens longer than 3 chars
            words = [w.lower() for w in text.split() if len(w) > 3]
            for unique_topic in articles_per_topic:
                nr = articles_per_topic[unique_topic]
                summa = -10
                for t in articles_per_topic:
                    summa += articles_per_topic[t]
                percent_log = log(nr / summa)
                for word in words:
                    # here we use this formula: log(h(c))=log(P(c))+log(P(w1|c))+⋯+log(P(wn|c))
                    if word in articles_words[unique_topic]:
                        percent_log += log((articles_words[unique_topic][word] + 1) / (
                                all_words_in_topic[unique_topic] + len(articles_words[unique_topic])))
                    else:
                        percent_log += log(1 / (all_words_in_topic[unique_topic] + len(articles_words[unique_topic])))
                if percent_log > article_right_topic_percent:
                    article_right_topic_percent = percent_log
                    article_right_topic = unique_topic
            text_analysis = "Right topic: " + topic + " - algorithm got: " + article_right_topic
            # print notice, if topic is wrong
            if topic != article_right_topic:
                print("The wrong expectation of topic.")
            results.append(text_analysis)
        return results


if __name__ == '__main__':
    print(test())
