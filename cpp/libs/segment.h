#ifndef SEGMENT_H
#define SEGMENT_H
namespace segment {
class Segment {
 public:
  Segment(int l, int r) {
    int m = (l + r) >> 1;
    if (l < r) {
      _l = new Segment(l, m);
      _r = new Segment(m + 1, r);
      pushUp();
    } else {
    }
  }

#define NO_INTERSECTION ql > r || qr < l
#define COVER ql <= l &&qr >= r
#define RANGE (min(qr, r) - max(l, ql) + 1)

  void update(int ql, int qr, int l, int r) {
    if (NO_INTERSECTION) {
      return;
    }
    if (COVER) {
      modify();
      return;
    }
    pushDown();
    int m = (l + r) >> 1;
    _l->update(ql, qr, l, m);
    _r->update(ql, qr, m + 1, r);
    pushUp();
  }

  void query(int ql, int qr, int l, int r) {
    if (NO_INTERSECTION) {
      return;
    }
    if (COVER) {
      return;
    }
    pushDown();
    int m = (l + r) >> 1;
    _l->query(ql, qr, l, m);
    _r->query(ql, qr, m + 1, r);
  }

#undef NO_INTERSECTION
#undef COVER

 private:
  Segment *_l, *_r;

  void pushDown() {}

  void pushUp() {}

  void modify() {}
};
}  // namespace segment

#endif  // SEGMENT_H
