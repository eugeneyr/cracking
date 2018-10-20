# Abbreviate "middle" parts of a name.

def abbreviate(name:str):
    if name is None:
        return None
    parts = name.split()
    if len(parts) > 2:
        parts = [parts[0]] + [part[0].upper() + '.' for part in parts[1:-1]] + [parts[-1]]
    return ' '.join(parts)

if __name__ == '__main__':
    print(abbreviate('   John Edgar Smith   '))
    print(abbreviate('   John Edgar Maynard Smith   '))
    print(abbreviate('   John Smith   '))
    print(abbreviate('   John   '))
    print(abbreviate(''))
