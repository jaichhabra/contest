#ifndef READER_H
#define READER_H

#include "common.h"

namespace reader {

class Input {
 private:
  istream &_in;

 public:
  Input(istream &is) : _in(is) {}
  template <class T>
  T read() {
    T tmp;
    _in >> tmp;
    return tmp;
  }
  int ri() { return read<int>(); }
  ll rl() { return read<ll>(); }
  double rd() { return read<double>(); }
  char rc() { return read<char>(); }
  string rs() { return read<string>(); }
};

class StringReader {
 private:
  const string &_s;
  int _offset;

 public:
  StringReader(const string &s) : _s(s), _offset(0) {}
  void skip() {
    while (_offset < _s.size() && _s[_offset] <= 32) {
      _offset++;
    }
  }
  bool hasMore() {
    skip();
    return _offset < _s.size();
  }
  int readInt() {
    skip();
    int ans = 0;
    bool sign = true;
    if (_s[_offset] == '-' || _s[_offset] == '+') {
      sign = _s[_offset] == '+';
    }
    while (_offset < _s.size() && '0' <= _s[_offset] && _s[_offset] <= '9') {
      ans = ans * 10 + _s[_offset] - '0';
      _offset++;
    }
    return ans;
  }
};

}  // namespace reader

#endif