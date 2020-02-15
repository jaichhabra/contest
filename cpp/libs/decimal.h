#ifndef DECIMAL_H
#define DECIMAL_H

namespace decimal
{
template <class T>
T RoundToInt(double x)
{
    return x >= 0 ? x + 0.5 : x - 0.5;
}
template <class T>
int Sign(T x)
{
    return x < 0 ? -1 : x > 0 ? 1 : 0;
}
template <class T>
bool IsPlusOverflow(T a, T b)
{
    if (Sign(a) != Sign(b))
    {
        return false;
    }
    if (a < 0)
    {
        return a + b > 0;
    }
    else
    {
        return a + b < 0;
    }
}
template <class T>
bool IsMultiplicationOverflow(T a, T b, T limit)
{
    if (limit < 0)
    {
        limit = -limit;
    }
    if (a < 0)
    {
        a = -a;
    }
    if (b < 0)
    {
        b = -b;
    }
    if (a == 0 || b == 0)
    {
        return false;
    }
    //a * b > limit => a > limit / b
    return a > limit / b;
}
} // namespace decimal

#endif